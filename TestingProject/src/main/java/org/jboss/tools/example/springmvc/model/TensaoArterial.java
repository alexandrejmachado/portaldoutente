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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@NamedQueries({
	@NamedQuery(name=TensaoArterial.FIND_ALL_BY_UTENTE, query="SELECT t FROM TensaoArterial t WHERE t.numUtente = :" + TensaoArterial.UTENTE +" ORDER BY t.data ASC")
})
public class TensaoArterial {

		public static final String UNIDADE = "mmHg";
		
		@JsonIgnore
		@Id
		@GeneratedValue
		private int id;
		
		public static final String UTENTE = "numUtente";
		
		public static final String FIND_ALL_BY_UTENTE = "TensaoArterial.findAllByUtente";
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date data;
		
		@NotNull
		private int max;
		
		@JsonIgnore
		@NotNull
		private String numUtente;
		
		@NotNull
		private int min;
		
		public TensaoArterial(){}
		
		public TensaoArterial(int max, int min, String numUtente){
			this.max = max;
			this.min = min;
			this.data = new Date();
			this.numUtente = numUtente;
		}

		public static String getUnidade() {
			return UNIDADE;
		}

		@JsonIgnore
		public int getId() {
			return id;
		}

		@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
		public Date getData() {
			return data;
		}

		@JsonIgnore
		public String getNumeroUtente() {
			return numUtente;
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
