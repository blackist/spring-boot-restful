package org.blackist.web.springbootor.web.system;

import io.swagger.annotations.ApiOperation;
import org.blackist.web.springbootor.common.response.Response;
import org.blackist.web.springbootor.service.system.WebLogService;
import org.blackist.web.springbootor.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    private final WebLogService webLogService;

    @Autowired
    public LogController(WebLogService webLogService) {
        this.webLogService = webLogService;
    }

    @ApiOperation("查询Web日志")
    @GetMapping("/web")
    public Response queryWebLogs() {

        return Response.SUCCESS(webLogService.findAll());
    }

}
