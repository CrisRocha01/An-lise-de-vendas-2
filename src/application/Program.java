package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		System.out.println();

		List<Sale> list = new ArrayList<>();
		sc.close();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				int month = Integer.parseInt(fields[0]);
				int year = Integer.parseInt(fields[1]);
				String name = fields[2];
				int items = Integer.parseInt(fields[3]);
				double total = Double.parseDouble(fields[4]);
				list.add(new Sale(month, year, name, items, total));
				line = br.readLine();

			}
			
			Set<String> set = new HashSet<>();
			
			for (Sale s : list) {
				set.add(s.getSeller());
			}
			

			for (String name : set) {
				double soma = list.stream().filter(x -> x.getSeller().equals(name)).map(x -> x.getTotal()).reduce(0.0, (a, b) -> a + b);
				System.out.printf("%s - R$ %.2f%n", name, soma);
			}

			

		} catch (IOException e) {
			System.out.printf("Erro: %s (O sistema não pode encontrar o arquivo especificado)", path);
		}

	}

}
