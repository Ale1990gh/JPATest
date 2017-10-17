package it.dstech.jpatest;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static ServicesCrud servicesCrud;
	private static List<?> resultList;
	private static Scanner in;

	public static void main(String[] args) {

		servicesCrud = new ServicesCrud();
		in = new Scanner(System.in);
		System.out.println("Cosa vuoi fare?");

		boolean exit = false;

		do {
			System.out.println("1 - Insert record in database");
			System.out.println("2 - Select tabella");
			System.out.println("3 - Update record");
			System.out.println("4 - Delete record");
			System.out.println("5 - Termina programma");

			int scelta = in.nextInt();
			in.nextLine();

			switch (scelta) {

			case 1:
				create();
				break;

			case 2:
				read();
				break;

			case 3:
				update();
				break;

			case 4:
				delete();
				break;

			case 5:
				servicesCrud.closeLogicaJPA();
				exit = true;
				break;
			}
		} while (!exit);

	}

	private static void create() {

		try {
			Address address = new Address();

			System.out.println("Inserisci via");
			address.setStreet(in.nextLine());

			System.out.println("Inserisci CAP");
			address.setPostcode(in.nextLine());

			System.out.println("Inserisci città");
			address.setCity(in.nextLine());

			System.out.println("Inserisci provincia");
			address.setProvince(in.nextLine());

			System.out.println("Inserisci stato");
			address.setCountry(in.nextLine());

			servicesCrud.jpaCreate(address);

			System.out.println("Record creato: " + address);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void read() {

		try {
			resultList = servicesCrud.jpaRead("SELECT a FROM Address a").getResultList();

			for (Object object : resultList)
				System.out.println(object);

			System.out.println("Lista completata");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void update() {

		try {
			int index = 1;

			for (Object object : resultList) {
				System.out.println(index + " - " + object);
				index++;
			}

			System.out.println("Seleziona l'elemento da aggiornare");

			int el = in.nextInt();
			in.nextLine();
			Address address = (Address) resultList.get(el - 1);

			System.out.println("Modificare via? y/n");
			String risp = in.nextLine().toLowerCase();
			if (risp.equals("y")) {
				System.out.println("Inserisci via");
				address.setStreet(in.nextLine());
			}
			System.out.println("Modificare CAP? y/n");
			risp = in.nextLine().toLowerCase();
			if (risp.equals("y")) {
				System.out.println("Inserisci CAP");
				address.setPostcode(in.nextLine());
			}
			System.out.println("Modificare città? y/n");
			risp = in.nextLine().toLowerCase();
			if (risp.equals("y")) {
				System.out.println("Inserisci città");
				address.setCity(in.nextLine());
			}
			System.out.println("Modificare provincia? y/n");
			risp = in.nextLine().toLowerCase();
			if (risp.equals("y")) {
				System.out.println("Inserisci provincia");
				address.setProvince(in.nextLine());
			}
			System.out.println("Modificare stato? y/n");
			risp = in.nextLine().toLowerCase();
			if (risp.equals("y")) {
				System.out.println("Inserisci stato");
				address.setCountry(in.nextLine());
			}

			servicesCrud.jpaUpdate(address);

			System.out.println("Record modificato: " + address);

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Devi inserire un indice valido");
			update();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void delete() {

		try {
			int index = 1;

			for (Object object : resultList) {

				System.out.println(index + " - " + object);
				index++;
			}

			System.out.println("Seleziona l'elemento da eliminare");

			int el = in.nextInt();
			in.nextLine();

			Object object = resultList.get(el - 1);

			servicesCrud.jpaDelete(object);

			System.out.println("Record eliminato :" + object);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Devi inserire un indice valido");
			delete();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
