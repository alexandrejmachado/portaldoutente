package org.jboss.tools.example.springmvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity

@NamedQueries({
	@NamedQuery(name=TensaoArterial.FIND_ALL_BY_UTENTE, query="SELECT t FROM TensaoArterial t WHERE t.clientId = :" + TensaoArterial.ID +" ORDER BY t.data DESC")
})
public class TensaoArterial {

		public static final String UNIDADE = "mmHg";
		
		@Id
		@GeneratedValue
		private int id;
		
		public static final String ID = "numUtente";
		
		public static final String FIND_ALL_BY_UTENTE = "TensaoArterial.findAllByUtente";
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date data;
		
		@NotNull
		private int max;
		
		@NotNull
		private int clientId;
		
		@NotNull
		private int min;
		
		public TensaoArterial(){}
		
		public TensaoArterial(int max, int min, int numUtente){
			this.max = max;
			this.min = min;
			this.data = new Date();
			this.clientId = numUtente;
		}

		public static String getUnidade() {
			return UNIDADE;
		}

		public int getId() {
			return id;
		}

		public Date getData() {
			return data;
		}

		public int getNumeroUtente() {
			return clientId;
		}

		
		public int getMax() {
			return max;
		}

		public int getMin() {
			return min;
		}
		
		public String toString(){
			return this.getMax() + "/" + this.getMin() + TensaoArterial.UNIDADE;
		}
}
