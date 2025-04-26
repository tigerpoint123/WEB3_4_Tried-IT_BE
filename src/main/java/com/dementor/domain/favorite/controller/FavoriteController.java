package com.dementor.domain.favorite.controller;

import com.dementor.domain.favorite.dto.response.FavoriteAddResponse;
import com.dementor.domain.favorite.dto.response.FavoriteFindResponse;
import com.dementor.domain.favorite.service.FavoriteService;
import com.dementor.global.ApiResponse;
import com.dementor.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{classId}")
    @Operation(summary = "즐겨찾기 등록", description = "회원이 특정 멘토링 수업을 즐겨찾기 추가합니다.")
    public ResponseEntity<ApiResponse<FavoriteAddResponse>> addFavorite(
            @PathVariable Long classId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        Long memberId = userDetails.getId();

        FavoriteAddResponse response = favoriteService.addFavorite(classId, memberId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.of(
                                true,
                                HttpStatus.OK,
                                "즐겨찾기 등록 성공",
                                response
                        )
                );
    }

    @DeleteMapping("/{favoriteId}")
    @Operation(summary = "즐겨찾기 삭제", description = "회원이 특정 멘토링 수업을 즐겨찾기 삭제합니다.")
    public ResponseEntity<ApiResponse<Void>> deleteFavorite(
            @PathVariable Long favoriteId
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.of(
                                true,
                                HttpStatus.OK,
                                "unfavorited"
                        )
                );
    }

    @GetMapping("/{mamberId}")
    @Operation(summary = "회원의 즐겨찾기 목록 조회", description = "회원이 등록한 즐겨찾기 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<Page<FavoriteFindResponse>>> getFavoriteList(
        @PathVariable Long mamberId,
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.of(
                                true,
                                HttpStatus.OK,
                                "favorited"
                        )
                );
    }

}
