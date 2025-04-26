package com.dementor.domain.favorite.service;

import com.dementor.domain.favorite.dto.response.FavoriteAddResponse;
import com.dementor.domain.favorite.entity.Favorite;
import com.dementor.domain.favorite.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteAddResponse addFavorite(Long classId, Long memberId) {
        Favorite favorite = Favorite.builder()
                .mentoringClassId(classId)
                .memberId(memberId)
                .build();

        favorite = favoriteRepository.save(favorite);

        return FavoriteAddResponse.of(favorite);
    }
}
