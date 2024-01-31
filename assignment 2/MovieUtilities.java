package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author Cagri Isilak, 210764050, isil4050@mylaurier.ca
 * @version 2022-10-04
 */
public class MovieUtilities {

    /**
     * Counts the number of movies in each genre given in Movie.GENRES. An empty
     * movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
     *
     * @param movies List of movies.
     * @return Number of genres across all Movies. One entry for each genre in the
     *         Movie.GENRES array.
     */
    public static int[] genreCounts(final ArrayList<Movie> movies) {

    	final int[] counts = new int[Movie.GENRES.length];

        for (Movie movie : movies) {
            int genreIndex = movie.getGenre();
            if (genreIndex >= 0 && genreIndex < counts.length) {
                counts[genreIndex]++;
            }
        }

        return counts;
    }

    /**
     * Creates a Movie object by requesting data from a user. Uses the format:
     *
     * <pre>
    Title:
    Year:
    Director:
    Rating:
    Genres:
    0: science fiction
    1: fantasy
    ...
    10: mystery

    Enter a genre number:
     * </pre>
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return A Movie object.
     */
    public static Movie getMovie(final Scanner keyboard) {

    	String title, director;
        int year, genre;
        double rating;

        System.out.print("Title: ");
        title = keyboard.nextLine();
        System.out.print("Year: ");
        year = keyboard.nextInt();
        keyboard.nextLine();
        System.out.print("Director: ");
        director = keyboard.nextLine();
        System.out.print("Rating: ");
        rating = keyboard.nextDouble();
        keyboard.nextLine();
        genre = readGenre(keyboard);

        return new Movie(title, year, director, rating, genre);
    }

    /**
     * Creates a list of Movies whose genre is equal to the genre parameter.
     *
     * @param movies List of movies.
     * @param genre  Genre to compare against.
     * @return List of movies of genre.
     */
    public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

    	final ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getGenre() == genre) {
                result.add(movie);
            }
        }

        return result;
    }

    /**
     * Creates a list of Movies whose ratings are equal to or higher than rating.
     *
     * @param movies List of movies.
     * @param rating Rating to compare against.
     * @return List of movies of rating or higher.
     */
    public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

    	final ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getRating() >= rating) {
                result.add(movie);
            }
        }

        return result;
    }

    /**
     * Creates a list of Movies from a particular year.
     *
     * @param movies List of movies.
     * @param year   Year to compare against.
     * @return List of movies of year.
     */
    public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {

    	ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getYear() == year) {
                result.add(movie);
            }
        }
        return result;
    }

    /**
     * Asks a user to select a genre from a list of genres displayed by calling
     * Movie.genresMenu() and returns an integer genre code. The genre must be a
     * valid index to an item in Movie.GENRES.
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return An integer genre code.
     */
    public static int readGenre(final Scanner keyboard) {

    	Movie.genresMenu();
        int genre = -1;
        while (genre < 0 || genre >= Movie.GENRES.length) {
            System.out.print("Enter genre code: ");
            if (keyboard.hasNextInt()) {
                genre = keyboard.nextInt();
            }
            keyboard.nextLine();
        }
        return genre;
    }

    /**
     * Creates and returns a Movie object from a line of formatted string data.
     *
     * @param line A vertical bar-delimited line of movie data in the format
     *             title|year|director|rating|genre
     * @return The data from line as a Movie object.
     */
    public static Movie readMovie(final String line) {

    	String[] fields = line.split("\\|");
        if (fields.length != 5) {
            return null;
        }
        String title = fields[0].trim();
        int year = Integer.parseInt(fields[1].trim());
        String director = fields[2].trim();
        double rating = Double.parseDouble(fields[3].trim());
        int genre = Integer.parseInt(fields[4].trim());
        return new Movie(title, year, director, rating, genre);
    }

    /**
     * Reads a list of Movies from a file.
     *
     * @param fileIn A Scanner of a Movie data file in the format
     *               title|year|director|rating|genre
     * @return A list of Movie objects.
     */
    public static ArrayList<Movie> readMovies(final Scanner fileIn) {

    	ArrayList<Movie> movies = new ArrayList<>();
        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            Movie movie = readMovie(line);
            if (movie != null) {
                movies.add(movie);
            }
        }
        return movies;
    }

    /**
     * Writes the contents of a list of Movie to a PrintStream.
     *
     * @param movies A list of Movie objects.
     * @param ps     Output PrintStream.
     */
    public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

    	for (Movie movie : movies) {
            ps.println(movie.getTitle() + " (" + movie.getYear() + ")");
            ps.println("Director: " + movie.getDirector());
            ps.println("Rating: " + movie.getRating() + "/10");
            ps.println("Genre: " + Movie.GENRES[movie.getGenre()]);
            ps.println();

	return;
    }
    }}
