package com.joun.sosmall.common.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.common.exception.InvalidRequestException;
import com.joun.sosmall.common.exception.LogicalConflictException;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "API 호출 성공"),

                @ApiResponse(responseCode = "404", description = "존재하지 않는 API", content = @Content(schema = @Schema(implementation = NotFoundException.class))),

                @ApiResponse(responseCode = "400", description = "유효성 검증 실패", content = @Content(schema = @Schema(implementation = InvalidRequestException.class))),

                @ApiResponse(responseCode = "409", description = "DB 데이터와 충돌", content = @Content(schema = @Schema(implementation = LogicalConflictException.class))),

// @ApiResponse(responseCode = "405", description = "잘못된
// Method 요청", content = @Content(schema = @Schema(implementation =
// RequestMethodNotSupportResponse.class))),

// @ApiResponse(responseCode = "401", description = "인증 실패", content =
// @Content(schema = @Schema(implementation = UnauthorizedResponse.class))),

// @ApiResponse(responseCode = ExpiredToken.CODE, description = "토큰 유효기간 만료",
// content = @Content(schema = @Schema(implementation =
// ExpiredTokenResponse.class))),

// @ApiResponse(responseCode = "403", description = "인가 실패(권한 없음)", content =
// @Content(schema = @Schema(implementation = ForbiddenResponse.class))),

})
public @interface ApiDocumentResponse {
}