package mack.ps2.estagios.estagios.controller;
import java.util.ArrayList;
import java.util.List;

import mack.ps2.estagios.estagios.model.Empresa;

@RestController

public class EmpresaController {
    private List<Empresa> empresas;

    public EmpresaController() {
        this.empresas = new ArrayList<>();
        empresas.add(new Empresa("Empresa Alfa LTDA", "12.345.678/0001-90","contato@empresa-alfa.com", 1));
        empresas.add(new Empresa("Beta Comércio ME", "98.765.432/0001-10","beta@comercio.com", 2));
        empresas.add(new Empresa("Gamma Serviços S.A.", "11.222.333/0001-44","servicos@gamma.com", 3));
        
    }
}
