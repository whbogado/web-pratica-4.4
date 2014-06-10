/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inscricao.convert;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Wilson
 */
@FacesConverter("cpfConverter")
public class CPFConverter implements Converter {

    private static final Pattern pattern =
        Pattern.compile("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$");
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (!pattern.matcher(string).matches()) {
            throw new ConverterException(new FacesMessage("CPF '" + string + "' com formato inválido"));
        }
        String cpf = string.replaceAll("\\.", "").replaceAll("-", "");
        return Long.parseLong(cpf);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (!(o instanceof Long)) {
            throw new ConverterException(new FacesMessage("CPF deve ser long"));
        }
        return String.format("%011d" , o);
    }
    
}
