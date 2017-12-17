package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.model.Movie;
import pl.com.bottega.cms.model.commands.Command;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.repositories.MovieRepository;

@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand> {

    private MovieRepository movieRepository;

    public CreateMovieHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void handle(CreateMovieCommand command) {
        Movie movie = new Movie(command);
        movieRepository.save(movie);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateMovieCommand.class;
    }
}
