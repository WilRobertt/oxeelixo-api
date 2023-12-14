package br.com.ifpe.oxeelixo.api.acesso;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoRequest implements Serializable {

    private String username;

    private String password;
    
}

