package br.com.santander.challenge.helper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import br.com.santander.challenge.model.Address;
import br.com.santander.challenge.model.Customer;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "nome", "idade", "cpf", "rg", "data_nasc", "sexo", "mae", "pai", "cep", "endereco",
			"numero", "bairro", "cidade", "estado", "telefone_fixo", "celular", "salario" };

	static String SHEET = "Transformed by Data.Page";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Customer> excelToCustomers(InputStream is) {
		try {
			
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Customer> customers = new ArrayList<Customer>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// escapa do cabecalho
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Customer customer = new Customer();
				Address address = new Address();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						customer.setNome(currentCell.getStringCellValue());
						break;

					case 1:
						customer.setIdade(Integer.parseInt(currentCell.getStringCellValue()));
						break;

					case 2:
						customer.setCpf(currentCell.getStringCellValue().replace(".", "").replace("-", ""));
						break;

					case 3:
						customer.setRg(currentCell.getStringCellValue());
						break;
						
					case 4:
						LocalDate ldDataNasc = LocalDate.parse(currentCell.getStringCellValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						customer.setDataNasc(ldDataNasc);
						
					case 5:
						customer.setSexo(currentCell.getStringCellValue());
						break;
					
					case 6:
						customer.setMae(currentCell.getStringCellValue());
						break;
					
					case 7:
						customer.setPai(currentCell.getStringCellValue());
						break;
						
					case 8:
						address.setCep(currentCell.getStringCellValue());
						break;
					
					case 9:
						address.setEndereco(currentCell.getStringCellValue());
						break;
					
					case 10:
						address.setNumero(currentCell.getStringCellValue());
						break;
						
					case 11:
						address.setBairro(currentCell.getStringCellValue());
						break;
						
					case 12:
						address.setCidade(currentCell.getStringCellValue());
						break;
						
					case 13:
						address.setEstado(currentCell.getStringCellValue());
						break;
						
					case 14:
						customer.setTelefone_fixo(currentCell.getStringCellValue());
						break;
						
					case 15:
						customer.setCelular(currentCell.getStringCellValue());
						break;
						
					case 16:
						customer.setSalario(new BigDecimal(currentCell.getNumericCellValue()));
						break;	
						
					default:
						break;
					}

					cellIdx++;
				}
				
				customer.setAddress(address);
				
				customers.add(customer);
			}

			workbook.close();

			return customers;
			
		} catch (IOException e) {
			throw new RuntimeException("falha ao parsear arquivo xlsx: " + e.getMessage());
		}
	}
}
