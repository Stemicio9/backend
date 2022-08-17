package com.mvpbe.mvpbe.utilities;

import com.mvpbe.mvpbe.entities.Scontrino;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CsvParser {

    public static List<Scontrino> readfile(){
        try {
            Resource resource = new ClassPathResource("jsonSchema.json");
            FileInputStream file = new FileInputStream(resource.getFile());
            Reader targetReader = new InputStreamReader(file);

            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public static List<Scontrino> parseCsv(List<String[]> csv){

        List<Scontrino> result = new LinkedList<>();

        for(int i = 1; i < csv.size(); i++){
            String[] row = csv.get(i);
            try{
                result.add(readLine(row));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return result;

    }




    public static Scontrino readLine(String[] row){

        Scontrino csvRow = new Scontrino();
        csvRow.setCap(row[0]);
        csvRow.setNegozio(row[1]);
        csvRow.setData(formatDateCustom(row[2]));
        csvRow.setOra(formatHour(row[3]));
        csvRow.setProfilo(row[4]);
        csvRow.setNumero(Double.parseDouble(row[5]));
        csvRow.setDataRegistrazione(formatDateCustom(row[6]));
        csvRow.setCodiceCliente(row[7]);
        csvRow.setRagioneSociale(row[8]);
//        csvRow.set(row[9]);
        csvRow.setCodiceArticolo(row[10]);
        csvRow.setDescrizione(row[11]);
        csvRow.setQuantita(Integer.parseInt(row[12]));
        csvRow.setImponibile(Double.parseDouble(row[13]));
        csvRow.setImposta(Double.parseDouble(row[14]));
        csvRow.setTotale(Double.parseDouble(row[15]));
        csvRow.setUltimoCosto(Double.parseDouble(row[16]));
        csvRow.setAdesso(formatDateCustom(row[17]));
        return csvRow;
    }


    public static Date formatDateCustom(String dateString){
        // format considerato --> dd/MM/yyyy


        try{
            String[] dateParts = dateString.split("/");


            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate dateObject = LocalDate.of(Integer.parseInt(dateParts[2]),  Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
            Date result = Date.from(dateObject.atStartOfDay(defaultZoneId).toInstant());
            if(dateObject.getYear() ==1899){
                return null;
            }
            return result;
        }  catch (Exception e) {

            //   e.printStackTrace();
            return null;
        }

    }


    public static Date formatHour(String dateString){
        // format considerato --> HH:mm


        try{
            String[] dateParts = dateString.split(":");

            int year = 1000;
            int month = 1;
            int day = 1;

            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate dateObject = LocalDate.of(year,  month, day);
            Date result = Date.from(dateObject.atStartOfDay(defaultZoneId).toInstant());
            result.setHours(Integer.parseInt(dateParts[0]));
            result.setMinutes(Integer.parseInt(dateParts[1]));
            if(dateObject.getYear() ==1899){
                return null;
            }
            return result;
        }  catch (Exception e) {

            //   e.printStackTrace();
            return null;
        }

    }



}
