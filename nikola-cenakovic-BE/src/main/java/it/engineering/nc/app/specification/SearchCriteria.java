package it.engineering.nc.app.specification;

import java.util.Objects;

public class SearchCriteria implements Comparable<SearchCriteria>{
	private String key;
    private Object value;
    private SearchOperation operation;
    
	public SearchCriteria() {
		super();
	}

	public SearchCriteria(String key, Object value, SearchOperation operation) {
		super();
		this.key = key;
		this.value = value;
		this.operation = operation;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public SearchOperation getOperation() {
		return operation;
	}

	public void setOperation(SearchOperation operation) {
		this.operation = operation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, operation, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchCriteria other = (SearchCriteria) obj;
		return Objects.equals(key, other.key) && operation == other.operation && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "SearchCriteria [key=" + key + ", value=" + value + ", operation=" + operation + "]";
	}
	
	@Override
	  public int compareTo(SearchCriteria sc) {
	    if (getKey() == null || sc.getKey() == null) {
	      return 0;
	    }
	    return getKey().compareTo(sc.getKey());
	  }
}
