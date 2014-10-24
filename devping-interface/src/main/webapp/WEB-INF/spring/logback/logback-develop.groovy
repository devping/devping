import ch.qos.logback.classic.boolex.OnMarkerEvaluator
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.filter.EvaluatorFilter
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.*
import static ch.qos.logback.core.spi.FilterReply.DENY
import static ch.qos.logback.core.spi.FilterReply.NEUTRAL

dailyRollingFileAppender = { args ->
    def appenderName = args.name
    def filename = args.file
    def logPattern = args.pattern ?: '%-5level %d{yyyy-MM-dd HH:mm:ss} [%thread] %logger{36} - %channel%n'
    def maxHistoryCount = args.maxHistory ?: 10 // 기본 10일
    def minLevel = args.level
    def logMarker = args.marker
    def compress = (args.compress == null ? true : args.compress)

    logbackParent.appender(appenderName, RollingFileAppender) {
        file = "/devping/logs${filename}"
        append = true

        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "/devping/logs${filename}.%d{yyyyMMdd}${compress ? '.gz' : ''}"
            maxHistory = maxHistoryCount
        }

        encoder(PatternLayoutEncoder) {
            pattern = logPattern
        }

        if (minLevel != null) {
            filter(ThresholdFilter) {
                level = minLevel
            }
        }

        if (logMarker != null) {
            filter(EvaluatorFilter) {
                evaluator(OnMarkerEvaluator) {
                    marker = logMarker
                }
                onMismatch = DENY
                onMatch = NEUTRAL
            }
        }
    }
}

/**
 * 'stdout' Appender 생성
 */
logbackParent.appender('stdout', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = '%-5level %d{yyyy-MM-dd HH:mm:ss} [%thread] %logger{36}.%method - %channel%n'
    }
    filter(EvaluatorFilter) {
        evaluator(OnMarkerEvaluator) {
            marker = ApiTrackingMarker.API_TRACKING_MARKER_NAME
        }
        onMismatch = NEUTRAL
        onMatch = DENY
    }
}

/**
 * errorFile Appender 생성
 */
dailyRollingFileAppender(name: 'errorFile', file: '/error_log/error.log', pattern: '%-5level %d{yyyy-MM-dd HH:mm:ss} [%thread] %logger{36} - %channel [%mdc]%n',
        maxHistory: 30, level: ERROR, compress: false)

def HOSTNAME = hostname
logbackParent = this

logger('org.jbug', DEBUG)
logger('org.springframework', DEBUG)

root(INFO, ['stdout'])
