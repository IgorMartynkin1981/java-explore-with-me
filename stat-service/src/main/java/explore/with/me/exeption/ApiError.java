package explore.with.me.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * description:	 Сведения об ошибке
 *
 * @errors List[String] Список стектрейсов или описания ошибок
 * @message String Сообщение об ошибке
 * @reason String Общее описание причины ошибки
 * @status    {@link StatusErrors} Код статуса HTTP-ответа
 * @timestamp String Дата и время когда произошла ошибка (в формате "yyyy-MM-dd HH:mm:ss")
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private List<String> errors;
    private String message;
    private String reason;
    private HttpStatus status;
    private String timestamp;

}
