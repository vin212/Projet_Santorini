package global;

public enum TypeLogger{
	INFO, WARNING, SEVERE;

	public String toString()
	{
		switch (this)
		{
			case INFO :
				return "INFO";
			case WARNING :
				return "WARNING";
			case SEVERE :
				return "SEVERE";
			default:
				return "";

		}
	}
}