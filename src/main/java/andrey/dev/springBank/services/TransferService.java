package andrey.dev.springBank.services;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TransferService {
    private int count;
    private int source;
    private int apply;
}
