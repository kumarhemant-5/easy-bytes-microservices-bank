package com.eazybytes.accounts.controller;
import ch.qos.logback.core.joran.spi.HttpUtil;
import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;


@Tag(
        name = "CURD REST APIs for Accounts in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

   @Operation(
           summary = "Create an account REST API",
           description = "REST API to create new Customer and Account inside EazyBank"
   )

   @ApiResponse(
           responseCode = "201",
           description = "HTTP Status CREATED"
   )
   @PostMapping("/create")
   public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
   }


   @Operation(
           summary = "Fetch Account Details REST API",
           description = "REST API to fetch Customer and Account details based on a mobile number"
   )

   @ApiResponse(
       responseCode = "200",
       description = "HTTP Status OK"
   )
   @RequestMapping(path = "/fetch", method = RequestMethod.GET)
   public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern (regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") @RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
   }

   @Operation(
           summary = "Update Account Details REST API",
           description = "REST API to update Customer and Account details based on a mobile number"
   )

   @ApiResponses({
           @ApiResponse(
                   responseCode = "200",
                   description = "HTTP Status OK"
           ),
           @ApiResponse(
                   responseCode = "417",
                   description = "Expectation Failed"
           ),

           @ApiResponse(
                   responseCode = "500",
                   description = "HTTP Status Internal Server Error",
                   content= @Content(
                           schema = @Schema(implementation = ErrorResponseDto.class)

                   )
           )
   }

   )
   @RequestMapping(path = "/update", method = RequestMethod.PUT)
   public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
       boolean isUpdated = iAccountsService.updateAccount(customerDto);
       if(isUpdated)
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
       else
           return ResponseEntity
                   .status(HttpStatus.EXPECTATION_FAILED)
                   .body(new ResponseDto(AccountConstants.STATUS_417 , AccountConstants.MESSAGE_417_UPDATE));
   }


   @Operation(
           summary = "Delete Account & Customer Details REST API",
           description = "REST API to delete Customer and Account details based on a mobile number"
   )

   @ApiResponses({
       @ApiResponse(
           responseCode = "200",
           description = "HTTP Status OK"
       ),

       @ApiResponse(
           responseCode = "500",
           description = "HTTP Status Internal Server Error"
       )
   })
   @DeleteMapping("/delete")
   public ResponseEntity<ResponseDto> deleteAccountDetails(@Pattern (regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") @RequestParam("mobileNumber") String nobileNumber) {
       boolean isDeleted = iAccountsService.deleteAccount(nobileNumber);
       if(isDeleted)
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
       else
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
   }
}
