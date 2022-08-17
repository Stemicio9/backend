package com.mvpbe.mvpbe.services;

import com.mvpbe.mvpbe.dto.SingleNegozioResponse;
import com.mvpbe.mvpbe.dto.TableResponse;
import com.mvpbe.mvpbe.entities.Scontrino;
import com.mvpbe.mvpbe.repository.ScontrinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.mvpbe.mvpbe.utilities.DateUtilsCustom.dateXDaysAgo;

@Service
public class ScontrinoService {


    @Autowired
    private ScontrinoRepository scontrinoRepository;



    public List<TableResponse> getScontriniByDate(Date data){
       Date ninety = dateXDaysAgo(data,90);
        Date year = dateXDaysAgo(data,365);
       List<TableResponse> result = this.scontrinoRepository.queryResultsbYdATE(data);
       for(TableResponse current : result){
           current.setImportoAnnuale(this.scontrinoRepository.incassoBetweenNegozio(year,data,current.getNegozio()));
           current.setImportoUltimi90(this.scontrinoRepository.incassoBetweenNegozio(ninety,data,current.getNegozio()));
       }
       return result;
    }

    public List<SingleNegozioResponse> getSingleNegozio(Date data, String negozio){
        return this.scontrinoRepository.queryCategoriesByNegozio(data, negozio);
    }

    public double scontrinoMedioDelGiorno(Date date){
        return this.scontrinoRepository.scontrinoMedioDelGiorno(date);
    }

    public long numeroVenditeDelGiorno(Date date){
        return this.scontrinoRepository.numeroVenditeDelGiorno(date);
    }

    public double incassoDelGiorno(Date date){
        return this.scontrinoRepository.incassoDelGiorno(date);
    }

    public double incassoDellaSettimana(Date date){
        Date week = dateXDaysAgo(date,7);
        return this.scontrinoRepository.incassoBetween(week,date);
    }
}
