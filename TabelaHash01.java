package si.arq.tabelaHash;

import java.util.ArrayList;
import java.util.Iterator;

public class TabelaHash01 {

    private ArrayList<Aluno>[] tabela;
    private final int CAPACIDADE_INICIAL = 20;

    //Cria uma nova tabela com a capacidade inicial = 20.
    public TabelaHash01() {
        this.tabela = new ArrayList[CAPACIDADE_INICIAL];
    }
    
    //Cria uma nova tabela com a capacidade passada como par�metro.
    public TabelaHash01(int capacidade) {
        this.tabela = new ArrayList[capacidade];
    }
    
    //Calcula o hash de uma determinada chave. A fun��o de hash � simples e usa o m�todo da divis�o.
    private int hash(Integer chave) {
        return chave % this.tabela.length;
    }
    
    //Calcula o hash utilizando o m�todo da multiplica��o.
    private int hashMultiplicacao(Integer chave) {
        double a = 0.617648934;
        double hash = chave*a;
        hash = (hash % 1) * this.tabela.length;
        return (int)hash;        
    }
    
    //Adiciona o par <chave,valor> na tabela.
    public void put(Integer chave, Aluno valor) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];
        
        if (alunos == null) {
            alunos = new ArrayList<Aluno>();
            alunos.add(valor);
            this.tabela[hash] = alunos;

        } else {
            for (int i = 0; i < alunos.size(); i++) {
                if (alunos.get(i).getMatricula() == chave) {
                    alunos.set(i, valor);
                    return;
                }
            }
            alunos.add(valor);
        }    
    }

    //Recupera o aluno cuja chave � igual a passada como par�metro.
    public Aluno get(Integer chave) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];
        
        if (alunos == null || alunos.isEmpty()) 
                return null;
        
        for (Aluno aluno : alunos) {
                if (aluno.getMatricula().equals(chave)){
                    return aluno;
                }
        }
        return null;
    }  

    //Remove o aluno cuja matr�cula � igual a chave passada como par�metro.
    public Aluno remove(int chave) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];
        
        Iterator<Aluno> it = alunos.iterator();
        Aluno atual = null;
        
        while (it.hasNext()) {
        	atual = it.next();
        	if (atual.getMatricula().equals(chave)) {
        		it.remove();
                return atual;
            }
        }
        
        return atual;
    }  

}
