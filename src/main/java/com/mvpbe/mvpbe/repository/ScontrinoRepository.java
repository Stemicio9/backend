package com.mvpbe.mvpbe.repository;

import com.mvpbe.mvpbe.dto.SingleNegozioResponse;
import com.mvpbe.mvpbe.dto.TableResponse;
import com.mvpbe.mvpbe.entities.Scontrino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScontrinoRepository extends JpaRepository<Scontrino,Long> {


    /*

      @Query("select distinct new com.mvpbe.mvpbe.dto.TableResponse(articoloDesc, count(articoloDesc), sum(quantita), depositoDesc) from CSVRow GROUP BY articoloDesc, depositoDesc order by sum(quantita) desc ")





 SELECT t.negozio AS negozio, SUM(t.totale) AS importoGiornaliero, COUNT(*) AS numeroScontrini, AVG(t.totale) AS prezzoMedio, MAX(t.ora) AS ultimaOra
FROM TABELLA t
WHERE t.data = :start
GROUP BY t.negozio




SELECT t.negozio AS negozio, SUM(t.totale) AS importoGiornaliero, COUNT(*) AS numeroScontrini, AVG(t.totale) AS prezzoMedio, MAX(t.ora) AS ultimaOra
FROM TABELLA t
WHERE t.data = :data AND t.negozio = :negozio
GROUP BY substr(t.codiceSS, 1,2)


    * */

    @Query("select new com.mvpbe.mvpbe.dto.TableResponse(s.negozio,SUM(s.totale),COUNT(s),AVG(s.totale),MAX(s.ora)) " +
            "FROM Scontrino s " +
            "WHERE s.data = :start " +
            "GROUP BY s.negozio")
    List<TableResponse> queryResultsbYdATE(
            @Param("start") Date start
            );


    @Query("select new com.mvpbe.mvpbe.dto.SingleNegozioResponse(substring(s.codice_ss, 1,2),s.negozio,SUM(s.totale),COUNT(s),AVG(s.totale),MAX(s.ora)) " +
            "FROM Scontrino s " +
            "WHERE s.data = :start AND s.negozio = :negozio " +
            "GROUP BY substring(s.codice_ss, 1,2)")
    List<SingleNegozioResponse> queryCategoriesByNegozio(
            @Param("start") Date start,
            @Param("negozio") String negozio
    );


    @Query("select SUM(s.totale) FROM Scontrino s WHERE s.data = :start")
    double incassoDelGiorno( @Param("start") Date start);

    @Query("select count(s) FROM Scontrino s WHERE s.data = :start")
    long numeroVenditeDelGiorno( @Param("start") Date start);

    @Query("select AVG(s.totale) FROM Scontrino s WHERE s.data = :start")
    double scontrinoMedioDelGiorno( @Param("start") Date start);

    @Query("select SUM(s.totale) FROM Scontrino s WHERE s.data between :start and :enddate")
    double incassoBetween( @Param("start") Date start, @Param("enddate") Date enddate);

    @Query("select SUM(s.totale) FROM Scontrino s WHERE s.negozio = :negozio and s.data between :start and :enddate")
    double incassoBetweenNegozio( @Param("start") Date start, @Param("enddate") Date enddate,  @Param("negozio") String negozio);

}
