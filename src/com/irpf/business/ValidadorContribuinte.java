package src.com.irpf.business;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidadorContribuinte {

    final String VERIFICA_NOME = "/[A-Z][a-z]* [A-Z][a-z]*/";
    final String VERIFICA_NUMERO_INTEIRO = "[0-9]";

    public static void validaNome(String nome) throws Exception{

        Pattern patternNome = new Pattern(VERIFICA_NOME);
        Matcher matcherNome = patternNome.matcher(nome);

        if (!matcher.find()) throw Exception("Nome deve ser preenchido apenas com letras.");
        else if(matcher.find() && nome.length < 3) throw Exception("Nome inválido.");
    }

    public static void validaIdade(String idade) throws Exception {

        Pattern patternIdade = new Pattern(VERIFICA_NUMERO_INTEIRO);
        Matcher matcherIdade = patternIdade.matcher(idade);


        if (!matcher.find()) throw Exception("Idade deve ser preenchida apenas com números.");
        else{
            int convIdade = Integer.parseInt(idade);
            if(convIdade < 0 || convIdade > 150) throw Exception("Idade inválida");
        }
    }

    public static void validaNDependentes(String nDependentes) throws Exception{

        Pattern patternNDependentes = new Pattern(VERIFICA_NUMERO_INTEIRO);
        Matcher matcherNDependentes = patternNDependetes.matcher(nDependentes);

        if (!matcher.find()) throw Exception("Número de dependentes deve ser preenchido apenas com números");
        else{
            int convNDependentes = Integer.parseInt(nDependetes);
            if(convNDependentes < 0) throw Exception("Número de dependentes inválido");
        }
    }

    public static void validaCpf(String cpf){}


}
