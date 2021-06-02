package animation;

public enum TypeAnimation 
{
	CLIGNOTER, AVANCER;

	public String toSrting()
	{
		switch(this)
		{
			case CLIGNOTER :
				return "CLIGNOTER";
			case AVANCER :
				return "AVANCER";
			default :
				return "BUG";
		}
	}
}