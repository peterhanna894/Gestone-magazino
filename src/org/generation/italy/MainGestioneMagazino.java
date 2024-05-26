package org.generation.italy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


import org.generation.italy.model.Movimento;



public class MainGestioneMagazino {
	/*
	 * Esercizio: gestione magazzino
Scrivere un programma per la gestione dei movimenti di un magazzino.

All'avvio del programma inizializzare delle tabelle codice/descrizione (HashMap), in particolare:

fornitori (cod, nome)
clienti (cod, nome)
prodotti (cod, descrizione)
tipologieMovimento (cod, descrizione)
E01: acquisto da fornitore
E02: reso da cliente
E03: produzione interna
E04: spostamento da altro magazzino
U01: vendita a cliente
U02: reso a fornitore
U03: sostituzione in garanzia
U04: spostamento a altro magazzino

Il programma deve proporre un menu con le seguenti operazioni:

1) inserimento movimento in entrata: viene chiesto all'utente di inserire i seguenti dati:
data
codice prodotto
quantità prodotto
codice movimento
riferimento (opzionale - cod fornitore (movimento E01), cod. cliente (movimento E02))

2) inserimento movimento in uscita: viene chiesto all'utente di inserire i seguenti dati:
data
codice prodotto
quantità prodotto
codice movimento
riferimento (opzionale - cod cliente (movimento U01), cod. cliente (movimento U02))

3) visualizzazione movimenti in entrata (vengono mostrate le informazioni inserite nel punto 1. 
decodificando i codici)

4) visualizzazione movimenti in uscita (vengono mostrate le informazioni inserite nel punto 2. 
decodificando i codici)

5) giacenza prodotto: viene chiesto di inserire il codice di un prodotto e ne viene calcolata la giacenza 
(differenza tra le quantità in entrata e le quantità in uscita)

NB: ad ogni nuovo movimento viene assegnato un codice univoco autoincrementante 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		HashMap<String, String> fornitori=new HashMap<String, String>();
		HashMap<String, String> clienti=new HashMap<String, String>();
		HashMap<String, String> prodotti=new HashMap<String, String>();
		HashMap<String, String> tipologieMovimento=new HashMap<String, String>();

		ArrayList<Movimento> elencoMovimenti=new ArrayList<Movimento>();

		Movimento m;
		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd/MM/yyyy");	//data cutomizzata dd(giorno) MM(mese) yyyy(anno 4 cifre)
		int sceltaMenu;			//menu principale
		String sceltaOpz;		//menu secondario
		int sceltaMenu2;
		String codiceProdottoScelto;
		int giacenza = 0;

	    fornitori.put("F01", "Alfa");
	    fornitori.put("F02", "Beta");
	    fornitori.put("F03", "Gamma");
	    fornitori.put("F04", "Delta");
	    fornitori.put("F05", "Epsilon");

	    clienti.put("C01", "Rossi");
	    clienti.put("C02", "Bianchi");
	    clienti.put("C03", "Verdi");
	    clienti.put("C04", "Neri");
	    clienti.put("C05", "Gialli");

	    prodotti.put("P01", "Prodotto A");
	    prodotti.put("P02", "Prodotto B");
	    prodotti.put("P03", "Prodotto C");
	    prodotti.put("P04", "Prodotto D");
	    prodotti.put("P05", "Prodotto E");

	    tipologieMovimento.put("E01", "acquisto da fornitore");
	    tipologieMovimento.put("E02", "reso da cliente");
	    tipologieMovimento.put("E03", "produzione interna");
	    tipologieMovimento.put("E04", "spostamento da altro magazzino");
	    tipologieMovimento.put("U01", "vendita a cliente");
	    tipologieMovimento.put("U02", "reso a fornitore");
	    tipologieMovimento.put("U03", "sostituzione in garanzia");
	    tipologieMovimento.put("U04", "spostamento a altro magazzino");
	    
	    do {
	    	
    		sceltaMenu2=0;

		    System.out.println("Menu Principale: Scegli il numero corrispondente");
		    System.out.println("(1) Inserimento movimento in entrata");
		    System.out.println("(2) Inserimento movimento in uscita");
		    System.out.println("(3) Visualizzazione movimenti in entrata");
		    System.out.println("(4) Visualizzazione movimenti in uscita");
		    System.out.println("(5) Visualizzazione giacenza prtodotti");
		    sceltaMenu=sc.nextInt();
		    sc.nextLine();
		    if(sceltaMenu==1){
		    	do {
		    		m=new Movimento();
			    	System.out.println("Stai inserendo un movimento in entrata");
			    	System.out.println("inserisci la data del movimento (gg/mm/aaaa): ");
			    	m.data=LocalDate.parse(sc.nextLine(), df);
			    	do{
			    		System.out.println("Inserisci il codice del prodotto");
			    		m.codiceProdotto=sc.nextLine().toUpperCase();
			    		if(!prodotti.containsKey(m.codiceProdotto))
			    			System.out.println("Codice prodotto non valido");
			    	}while(!prodotti.containsKey(m.codiceProdotto));
			    	
			   // verificaCodice(sc, prodotti, mE);,
			    	
			    	System.out.println("Inserisci la quantitá del prodotto");
			    	m.quantitaProdotto=sc.nextInt();
			    	sc.nextLine();
			    	
			    	do{
			    		System.out.println("Inserisci il codice del movimento");
			    		m.codiceMovimento=sc.nextLine().toUpperCase();
			    		if(!tipologieMovimento.containsKey(m.codiceMovimento))
			    			System.out.println("Codice movimento non valido");
			    	}while(!tipologieMovimento.containsKey(m.codiceMovimento));
			    	
			    	if(m.codiceMovimento.equalsIgnoreCase("E01")) {
			    		System.out.println("vuoi inserire il codice fornitore?(s/n)");
			    		sceltaOpz=sc.nextLine();
			    		if(sceltaOpz.equalsIgnoreCase("s")) {
			    			do{
					    		System.out.println("Inserisci il codice fornitore");
					    		m.riferimento=sc.nextLine().toUpperCase();
					    		if(!fornitori.containsKey(m.riferimento))
					    			System.out.println("Codice fornitore non valido");
					    	}while(!fornitori.containsKey(m.riferimento));
			    			
			    		}
			    	}
			    	if(m.codiceMovimento.equalsIgnoreCase("E02")) {
			    		System.out.println("vuoi inserire il codice cliente?(s/n)");
			    		sceltaOpz=sc.nextLine();
			    		if(sceltaOpz.equalsIgnoreCase("s")) {
			    			do{
					    		System.out.println("Inserisci il codice cliente");
					    		m.riferimento=sc.nextLine().toUpperCase();
					    		if(!clienti.containsKey(m.riferimento))
					    			System.out.println("Codice cliente non valido");
					    	}while(!clienti.containsKey(m.riferimento));
			    		}
			    	}
			    	
			    	elencoMovimenti.add(m);
			    	System.out.println("Quantitá movimenti" + elencoMovimenti.size());
			      	System.out.println("(1) per andare al menú prencipale");
			    	System.out.println("(2) per inserire un altro movimento in uscita");
	
			    	sceltaMenu2=sc.nextInt();
			    	sc.nextLine();
		    	}while(sceltaMenu2==2);
		    }else if(sceltaMenu==2) {
		    	do {
			    	m=new Movimento();
			    	System.out.println("Stai inserendo un movimento in uscita");
			    	System.out.println("inserisci la data del movimento (gg/mm/aaaa): ");
			    	m.data=LocalDate.parse(sc.nextLine(), df);
			    	
			    	do{
			    		System.out.println("Inserisci il codice del prodotto");
			    		m.codiceProdotto=sc.nextLine().toUpperCase();
			    		if(!prodotti.containsKey(m.codiceProdotto))
			    			System.out.println("Codice prodotto non valido");
			    	}while(!prodotti.containsKey(m.codiceProdotto));
			    	
			    	System.out.println("Inserisci la quantitá del prodotto");
			    	m.quantitaProdotto=(sc.nextInt()*-1);
			     	sc.nextLine();
			    	do{
			    		System.out.println("Inserisci il codice del movimento");
			    		m.codiceMovimento=sc.nextLine().toUpperCase();
			    		if(!tipologieMovimento.containsKey(m.codiceMovimento))
			    			System.out.println("Codice movimento non valido");
			    	}while(!tipologieMovimento.containsKey(m.codiceMovimento));
			    	
			    	if(m.codiceMovimento.equalsIgnoreCase("U02")) {
			    		System.out.println("vuoi inserire il codice fornitore?(s/n)");
			    		sceltaOpz=sc.nextLine();
			    		if(sceltaOpz.equalsIgnoreCase("s")) {
			    			do{
					    		System.out.println("Inserisci il codice fornitore");
					    		m.riferimento=sc.nextLine().toUpperCase();
					    		if(!fornitori.containsKey(m.riferimento))
					    			System.out.println("Codice fornitore non valido");
					    	}while(!fornitori.containsKey(m.riferimento));
			    		}
			    	}
			    	if(m.codiceMovimento.equalsIgnoreCase("U01")) {
			    		System.out.println("vuoi inserire il codice cliente?(s/n)");
			    		sceltaOpz=sc.nextLine();
			    		if(sceltaOpz.equalsIgnoreCase("s")) {
			    			do{
					    		System.out.println("Inserisci il codice cliente");
					    		m.riferimento=sc.nextLine().toUpperCase();
					    		if(!clienti.containsKey(m.riferimento))
					    			System.out.println("Codice cliente non valido");
					    	}while(!clienti.containsKey(m.riferimento));
			    		}
			    	}
			    	elencoMovimenti.add(m);
			    	System.out.println("Quantitá movimenti" + elencoMovimenti.size());
			    	System.out.println("(1) per andare al menú prencipale");
			    	System.out.println("(2) per inserire un altro movimento in uscita");

	
			    	sceltaMenu2=sc.nextInt();
			    	sc.nextLine();
		    	}while(sceltaMenu2==2);
		    }else if(sceltaMenu==3) {
		    	System.out.println("Elenco di tutti i movimenti in entrata: ");
		    	for(int i=0;i<elencoMovimenti.size();i++) {
		    		System.out.println("Movimento nr "+ (i+1));
		    		System.out.println("data: "+ elencoMovimenti.get(i).data.format(df));
					System.out.print("Codice prodotto: "+elencoMovimenti.get(i).codiceProdotto);
					System.out.println(", Nome prodotto: "+prodotti.get(elencoMovimenti.get(i).codiceProdotto));
	
					System.out.println("Quantitá prodotto: "+elencoMovimenti.get(i).quantitaProdotto);
					System.out.print("Codice movimento: "+elencoMovimenti.get(i).codiceMovimento);		
					System.out.println(", Descrizione movimento: "+tipologieMovimento.get(elencoMovimenti.get(i).codiceMovimento));
					if(elencoMovimenti.get(i).codiceMovimento.equals("E02")) {
						System.out.println("Cliente: "+ elencoMovimenti.get(i).riferimento+", "
								+clienti.get(elencoMovimenti.get(i).riferimento));
					}else if(elencoMovimenti.get(i).codiceMovimento.equals("E01")) {
						System.out.println("Fornitore: "+ elencoMovimenti.get(i).riferimento+", "
								+fornitori.get(elencoMovimenti.get(i).riferimento));
					   
					}
						
		    	}
		    	 System.out.println("Per andare al menú principale premi (1)");
				    sceltaMenu2=sc.nextInt();
				    sc.nextLine();
		    }else if(sceltaMenu==4) {
		    	System.out.println("Elenco di tutti i movimenti in uscita: ");
		    	for(int i=0;i<elencoMovimenti.size();i++) {
		    		System.out.println("Movimento nr "+ (i+1));
		    		System.out.println("data: "+ elencoMovimenti.get(i).data.format(df));
					System.out.print("Codice prodotto: "+elencoMovimenti.get(i).codiceProdotto);
					System.out.println(", Nome prodotto: "+prodotti.get(elencoMovimenti.get(i).codiceProdotto));
	
					System.out.println("Quantitá prodotto: "+elencoMovimenti.get(i).quantitaProdotto);
					System.out.print("Codice movimento: "+elencoMovimenti.get(i).codiceMovimento);		
					System.out.println(", Descrizione movimento: "+tipologieMovimento.get(elencoMovimenti.get(i).codiceMovimento));
					if(elencoMovimenti.get(i).codiceMovimento.equals("U01")) {
						System.out.println("Cliente: "+ elencoMovimenti.get(i).riferimento+", "
								+clienti.get(elencoMovimenti.get(i).riferimento));
					}else if(elencoMovimenti.get(i).codiceMovimento.equals("U02")) {
						System.out.println("Fornitore: "+ elencoMovimenti.get(i).riferimento+", "
								+fornitori.get(elencoMovimenti.get(i).riferimento));
					}
				    
		    	}
		    	System.out.println("Per andare al menú principale premi (1)");
			    sceltaMenu2=sc.nextInt();
			    sc.nextLine();	
		    }else if(sceltaMenu==5) {
		    	System.out.println("Scrivi il codice del prodotto di cui vuoi verificare la giacenza: ");
		    	codiceProdottoScelto=sc.nextLine();
		    	for(int i=0;i<elencoMovimenti.size();i++) {
		    		if(elencoMovimenti.get(i).codiceProdotto==codiceProdottoScelto)
		    			giacenza=giacenza+elencoMovimenti.get(i).quantitaProdotto;
		    	}
		    	
		    	
		   
		    	System.out.println(giacenza);
			    System.out.println("Per andare al menú principale premi (1)");
			    sceltaMenu2=sc.nextInt();
			    sc.nextLine();
		    }

	    }while(sceltaMenu2==1);
	}

	/*private static String verificaCodice(Scanner sc, HashMap<String, String> elencoValori, String messaggio) {
		do{
			System.out.println(messaggio);
			String codice = sc.nextLine().toUpperCase();
			if(!elenco.containsKey(codice))
				System.out.println("Codice non valido");
		}while(!elenco.containsKey(codice));
		System.out.println("Hai selezionato: "+elencoValori.get(codice));
		return codice;
	}*/

}
