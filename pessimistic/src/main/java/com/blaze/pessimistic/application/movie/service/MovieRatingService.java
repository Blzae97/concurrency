package com.blaze.pessimistic.application.movie.service;

import com.blaze.pessimistic.application.movie.mapper.MovieRatingMapper;
import com.blaze.pessimistic.domain.movie.adapter.MovieRatingAdapter;
import com.blaze.pessimistic.domain.movie.dto.MovieRatingDTO;
import com.blaze.pessimistic.domain.movie.repository.MovieRatingJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieRatingService {
    private final MovieRatingJpaRepository movieRatingJpaRepository;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public MovieRatingService(MovieRatingJpaRepository movieRatingJpaRepository) {
        this.movieRatingJpaRepository = movieRatingJpaRepository;
    }

    @Transactional
    public MovieRatingDTO registerMovieRating(Long movieFilmId, Long movieFilmRating) {
        MovieRatingAdapter movieRatingAdapter = movieRatingJpaRepository.findByMovieFilmId(movieFilmId);

        LOG.info("[{}] {} 아이디에 쓰기 락 적용", Thread.currentThread().getId(), movieFilmId);

        // 전체 평가 수 1 증가
        movieRatingAdapter.voterIncrease(1L);

        // 사용자 평가 score에 추가
        movieRatingAdapter.totalScoreIncrease(movieFilmRating);

        // 전체 평가 재 계산
        movieRatingAdapter.reCalculationMovieFilmRating();

        // db 반영
        MovieRatingAdapter savedMovieRatingAdapter = movieRatingJpaRepository.save(movieRatingAdapter.getMovieRating());

        MovieRatingMapper movieRatingMapper = new MovieRatingMapper();
        return movieRatingMapper.toDTO(savedMovieRatingAdapter.getMovieRating());
    }

}
