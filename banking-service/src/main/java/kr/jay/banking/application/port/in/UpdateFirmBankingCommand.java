package kr.jay.banking.application.port.in;

import javax.validation.constraints.NotNull;

import kr.jay.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmBankingCommand extends SelfValidating<UpdateFirmBankingCommand> {
    @NotNull
    private final String firmBankingAggregateIdentifier;

    @NotNull
    private final int status;

}
