package dw.editora;

import java.util.List;

public class ListaArtigos {

	private List<Long> artigos; //lista de id's de artigos
	public ListaArtigos() {}
	public List<Long> getArtigos(){
		return artigos;
	}
	public void setArtigos(List<Long> lartigos){
		artigos = lartigos;
	}
}
