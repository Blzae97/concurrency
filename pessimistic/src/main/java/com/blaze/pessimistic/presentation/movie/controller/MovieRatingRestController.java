package com.blaze.pessimistic.presentation.movie.controller;

import com.blaze.pessimistic.application.movie.service.MovieRatingService;
import com.blaze.pessimistic.domain.movie.dto.MovieRatingDTO;
import com.blaze.pessimistic.presentation.movie.dto.MovieRatingRegisterRequest;
import com.blaze.pessimistic.presentation.movie.dto.MovieRatingRegisterResponse;
import com.blaze.pessimistic.presentation.movie.mapper.MovieRatingRegisterResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieRatingRestController {

    private final MovieRatingService movieRatingService;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public MovieRatingRestController(MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @PostMapping(value = "/rating/register")
    public MovieRatingRegisterResponse registerRating(@RequestBody MovieRatingRegisterRequest movieRatingRegisterRequest) {
        MovieRatingDTO movieRatingDTO = movieRatingService.registerMovieRating(
                movieRatingRegisterRequest.getMovieFilmId(),
                movieRatingRegisterRequest.getMovieFilmRating()
        );

        LOG.info("[{}] {} 아이디에 쓰기 락 해제", Thread.currentThread().getId(), movieRatingRegisterRequest.getMovieFilmId());

        MovieRatingRegisterResponseMapper mapper = new MovieRatingRegisterResponseMapper();
        return mapper.toResponse(movieRatingDTO);
    }
}
