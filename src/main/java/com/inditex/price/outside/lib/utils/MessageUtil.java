package com.inditex.price.outside.lib.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.text.MessageFormat;

@Component
@Slf4j
public class MessageUtil {

    public String getFormattedMsg(String msg, Object... params) {
        MessageFormat messageFormat = new MessageFormat(msg);
        String formattedMsg = messageFormat.format(params);
        log.debug("getFormattedMsg - message: {}, params: {}, formatted-message: {}", msg, params, formattedMsg);
        return formattedMsg;
    }

}
