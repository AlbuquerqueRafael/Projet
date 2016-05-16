package models.enums;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.EnumValue;

/**
 * Created by Mafra on 15/04/16.
 */
public enum DiaDaSemana {
	@EnumValue("0")
	SEGUNDA,
	@EnumValue("1")
	TERCA,
	@EnumValue("2")
	QUARTA,
	@EnumValue("3")
	QUINTA,
	@EnumValue("4")
	SEXTA;
}