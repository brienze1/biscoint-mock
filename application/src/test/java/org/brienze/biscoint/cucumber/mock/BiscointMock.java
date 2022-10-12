package org.brienze.biscoint.cucumber.mock;

import org.brienze.biscoint.dto.BiscointDto;
import org.brienze.biscoint.dto.BitcoinDto;
import org.brienze.biscoint.enums.Quote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BiscointMock {

    private BigDecimal btcUnitaryBuyValue;
    private BigDecimal btcUnitarySellValue;

    @GetMapping("/v2/ticker")
    public BiscointDto generateToken() {
        BitcoinDto bitcoinDto = new BitcoinDto();
        bitcoinDto.setBuyValue(btcUnitaryBuyValue);
        bitcoinDto.setSellValue(btcUnitarySellValue);
        bitcoinDto.setBase(Quote.BTC);
        bitcoinDto.setQuote(Quote.BRL);

        BiscointDto biscointDto = new BiscointDto();
        biscointDto.setBitcoinDto(bitcoinDto);

        return biscointDto;
    }

    public void setBtcUnitaryBuyValue(BigDecimal btcUnitaryBuyValue) {
        this.btcUnitaryBuyValue = btcUnitaryBuyValue;
    }

    public void setBtcUnitarySellValue(BigDecimal btcUnitarySellValue) {
        this.btcUnitarySellValue = btcUnitarySellValue;
    }
}
