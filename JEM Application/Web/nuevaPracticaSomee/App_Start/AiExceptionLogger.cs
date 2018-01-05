﻿using System.Web.Http.ExceptionHandling;
using Microsoft.ApplicationInsights;

namespace nuevaPracticaSomee.App_Start
{
    public class AiExceptionLogger : ExceptionLogger
    {
        public override void Log(ExceptionLoggerContext context)
        {
            if (context != null && context.Exception != null)
            {
                var ai = new TelemetryClient();
                ai.TrackException(context.Exception);
            }
            base.Log(context);
        }
    }
}