package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.DelaiContractuel;

import java.util.List;

public interface DelaiContractuelService {
    DelaiContractuel create(DelaiContractuel delai);
    List<DelaiContractuel> getByContrat(Long contratId);
}
