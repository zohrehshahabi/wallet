package ir.mapsa.wallet.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class BaseDto {
    private Date createdDate;
    private Date lastModifiedDate;
}
