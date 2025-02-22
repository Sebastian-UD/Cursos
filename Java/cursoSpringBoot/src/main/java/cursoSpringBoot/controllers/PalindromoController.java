package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromoController {

    @GetMapping("/palindromo/{palabra}")
    public String validatePalindromo(@PathVariable String palabra){
        boolean resultado = validate(palabra);
        if(resultado)
            return "La palabra " + palabra + " es un palindromo";
        else
            return "La palabra " + palabra + " NO es un palindromo";
    }

    private boolean validate(String palabra){
        boolean respuesta = true;
        int i=0, j=palabra.length()-1;
        while(j>=0){
            if(palabra.charAt(i)!=palabra.charAt(j)){
                respuesta = false;
                break;
            }
            i++;
            j--;
        }
        return respuesta;
    }
}
