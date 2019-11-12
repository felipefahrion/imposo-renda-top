package src.com.irpf.business;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidadorContribuinte {

    private final String VERIFICA_NOME = "/[A-Z][a-z]* [A-Z][a-z]*/";
    private final String VERIFICA_NUMERO_INTEIRO = "[0-9]";

    public void validaNome(String nome) throws Exception{

        Pattern patternNome = new Pattern(VERIFICA_NOME);
        Matcher matcherNome = patternNome.matcher(nome);

        if (!matcher.find()) throw Exception("Nome deve ser preenchido apenas com letras.");
        else if(matcher.find() && nome.length < 3) throw Exception("Nome inválido.");
    }

    public void validaIdade(String idade) throws Exception {

        Pattern patternIdade = new Pattern(VERIFICA_NUMERO_INTEIRO);
        Matcher matcherIdade = patternIdade.matcher(idade);


        if (!matcher.find()) throw Exception("Idade deve ser preenchida apenas com números.");
        else{
            int convIdade = Integer.parseInt(idade);
            if(convIdade < 0 || convIdade > 150) throw Exception("Idade inválida");
        }
    }

    public void validaNDependentes(String nDependentes) throws Exception{

        Pattern patternNDependentes = new Pattern(VERIFICA_NUMERO_INTEIRO);
        Matcher matcherNDependentes = patternNDependetes.matcher(nDependentes);

        if (!matcher.find()) throw Exception("Número de dependentes deve ser preenchido apenas com números");
        else{
            int convNDependentes = Integer.parseInt(nDependetes);
            if(convNDependentes < 0) throw Exception("Número de dependentes inválido");
        }
    }

    public void validaCpf(String cpf) throws Exception{
        Pattern patternCPF = new Pattern("/d{3}./d{3}-/d");
        Matcher matcherCPF = patternCPF.matcher(cpf);

        if(cpf = null) throw Excpetion("Necessário informar on número de cpf");
        else if(!matcher.find()) throw Exception("CPF preenchido incorretamente, deve possuir o formato: xxx.xxx-xx");
    }

    public void validaContbPrevSocial(String contbPrevSocial) throws Exception{
        Matcher nLetras  = (new Pattern(VERIFICA_NOME)).matcher(contbPrevSocial);
        if(matcher.find()) throw Exception("Campo deve ser preenchido apenas com números");
        try{
            double valor = Double.valueOf(contbPrevSocial);
        }
        catch(Excetion e){ throw Exception("Contribuição previdenciária social é um campo númerico.");}
    }


}
