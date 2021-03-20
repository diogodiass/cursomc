package com.diogoamorim.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.diogoamorim.cursomc.domain.enums.TipoCliente;
import com.diogoamorim.cursomc.dto.ClienteNewDTO;
import com.diogoamorim.cursomc.resources.exception.FieldMessage;
import com.diogoamorim.cursomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Invalido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Invalido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
