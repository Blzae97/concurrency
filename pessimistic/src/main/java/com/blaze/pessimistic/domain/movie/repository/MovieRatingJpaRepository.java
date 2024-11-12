package com.blaze.pessimistic.domain.movie.repository;

import com.blaze.pessimistic.domain.movie.adapter.MovieRatingAdapter;
import com.blaze.pessimistic.domain.movie.entity.MovieRating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MovieRatingJpaRepository {
    @PersistenceContext
    private EntityManager em;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Transactional
    public MovieRatingAdapter save(MovieRating movieRating) {

        if (movieRating.getMovieRatingId() == null) {
            em.persist(movieRating);
        } else {
            em.merge(movieRating);
        }

        return new MovieRatingAdapter(movieRating);
    }

    @Transactional
    public MovieRatingAdapter findByMovieFilmId(Long movieFilmId) {
        String jpql = """
                SELECT mr\s
                FROM MovieRating mr\s
                WHERE mr.movieFilmId = :movieFilmId
                """;

        TypedQuery<MovieRating> movieRatingTypedQuery = em.createQuery(jpql, MovieRating.class);
        movieRatingTypedQuery.setParameter("movieFilmId", movieFilmId);
        movieRatingTypedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        movieRatingTypedQuery.setHint("jakarta.persistence.lock.timeout", 10000);

        return new MovieRatingAdapter(movieRatingTypedQuery.getSingleResult());
    }


}
