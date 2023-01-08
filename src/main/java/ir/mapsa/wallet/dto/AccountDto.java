package ir.mapsa.wallet.dto;


import ir.mapsa.wallet.base.BaseDto;
import ir.mapsa.wallet.entities.Role;
import ir.mapsa.wallet.entities.Wallet;

import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AccountDto extends BaseDto {
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, max = 12)
    private String password;

    private Set<Role> roles = new HashSet<>();

    private List<Wallet> wallets = new ArrayList<>();

}
