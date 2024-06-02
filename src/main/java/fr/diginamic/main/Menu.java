package fr.diginamic.main;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Actor;
import fr.diginamic.entity.Movie;
import fr.diginamic.filehandler.ReaderUtil;
import fr.diginamic.filehandler.WriterService;
import fr.diginamic.scan.ScanUtil;

public class Menu {

	static {
		System.out.println("Welcome !");
	}

	public static void startMenu() {

		Boolean condition = true;

		if (condition) {

			System.out.println("Would you like to load a database ?" + "\n" + "Y\\N (exit)");

			Boolean responseLoad = MenuUtil.askYN();

			if (responseLoad) {
				ArrayList<Path> pathToUse = ReaderUtil.checkFile();
				System.out
						.println("The directory you indidacted is valid.." + "\n" + "Proceed ?" + "\n" + "Y\\N (exit)");

				Boolean responseProceed = MenuUtil.askYN();

				if (responseProceed) {
					EntityManagerFactory emf = Persistence.createEntityManagerFactory("film-create");

					WriterService writerService = new WriterService(emf, pathToUse);
					writerService.writeSequence();
					System.out.println("Load succesfull !");
					emf.close();
				}

			}
			mainMenu();

		}
	}

	public static void mainMenu() {

		EntityManagerFactory emUse = Persistence.createEntityManagerFactory("film-use");
		EntityManager emU = emUse.createEntityManager();
		
		while (true) {
			System.out.println("1. Affichage de la filmographie d’un acteur donné\r\n"
					+ "2. Affichage du casting d’un film donné\r\n"
					+ "3. Affichage des films sortis entre 2 années données\r\n"
					+ "4. Affichage des films communs à 2 acteurs/actrices donnés.\r\n"
					+ "5. Affichage des acteurs communs à 2 films donnés\r\n"
					+ "6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au\r\n"
					+ "casting\r\n" + "0. Fin de l’application");

			String answer = ScanUtil.askString();
			switch (answer) {
			case "0":
				MenuUtil.goodBye();
			case "1":
				String searchActor = ScanUtil.askString();
				TypedQuery<Actor> querySelectActor = emU.createQuery("select a Actor a where a.nameOfActor = p1:", Actor.class);
				querySelectActor.setParameter("p1", searchActor);
				
				List<Actor> actor = querySelectActor.getResultList();
				List<Movie> movie;
				TypedQuery<Movie> querySelectMovie = emU.createQuery("select m Movie m join m.actorsOfMovie am join am.actorFk ama where ama.actorImdbId = :p1", Movie.class);

				if(actor.size() >0 && actor.size()<=1) {
					
					querySelectMovie.setParameter("p1", actor.get(0).getActorImdbId());
					movie = querySelectMovie.getResultList();
					for(Movie m : movie) {
						m.toString();
					}
				} else if(actor.size()>1) {
					for(Actor a : actor) {
						querySelectMovie.setParameter("p1", a.getActorImdbId());
						movie = querySelectMovie.getResultList();
						for(Movie m : movie) {
							m.toString();
						}
					}
				} else {
					System.out.println("Can't find actor... try again");
					continue;
				}
			case "2":
				String searchMovie = ScanUtil.askString();
				TypedQuery<Movie> querySelectMovieC2 = emU.createQuery("select m Movie a where m.nameOfMovie = :p1", Movie.class);
				querySelectMovieC2.setParameter("p1", searchMovie);
				List<Movie> movieC2 = querySelectMovieC2.getResultList();
				
				TypedQuery<Actor> querySelectActorC2 = emU.createQuery("select a Actor a join a.moviesOfActor ma join ma.movieFk mam where mam.movieImdbId= :p1", Actor.class);
				querySelectActorC2.setParameter("p1", movieC2.get(0).getMovieImdbId());
				


			case "3":
			case "4":
			case "5":
			case "6":

			}
		}

	}
}
