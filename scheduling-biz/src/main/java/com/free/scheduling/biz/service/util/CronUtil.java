package com.free.scheduling.biz.service.util;

import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import com.cronutils.model.time.ExecutionTime;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static com.cronutils.model.CronType.QUARTZ;

/**
 * @author funanbing
 * @date 2025-11-24 09:57:16
 * @description
 */
public class CronUtil {

    private static CronParser parser;

    static {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        CronUtil.parser = new CronParser(cronDefinition);
    }


    public static LocalDateTime buildNextDate(String cornExpr) {
        Assert.isTrue(!StringUtils.hasText(cornExpr),"Cron expression cannot be null or empty");
        try {
            ZonedDateTime now = ZonedDateTime.now();
            return getNextDateTime(cornExpr, now);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse cron expression: " + cornExpr, e);
        }
    }

    public static LocalDateTime buildNextDate(String cornExpr, LocalDateTime dateTime) {
        Assert.isTrue(!StringUtils.hasText(cornExpr),"Cron expression cannot be null or empty");
        Assert.notNull(dateTime, "DateTime cannot be null");
        try {
            ZonedDateTime zonedTime = dateTime.atZone(ZoneId.systemDefault());
            return getNextDateTime(cornExpr, zonedTime);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse cron expression: " + cornExpr, e);
        }
    }
    
    private static LocalDateTime getNextDateTime(String cornExpr, ZonedDateTime dateTime) {
        ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(cornExpr));
        Optional<ZonedDateTime> zonedDateTime = executionTime.nextExecution(dateTime);
        return zonedDateTime.orElseThrow(() -> new RuntimeException("Unable to calculate next execution time for cron expression: " + cornExpr))
                .toLocalDateTime();
    }
}