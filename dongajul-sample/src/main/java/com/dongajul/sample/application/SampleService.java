package com.dongajul.sample.application;

import com.dongajul.sample.application.port.in.ReadSampleDetail;
import com.dongajul.sample.application.port.in.RegisterSampleUseCase;
import com.dongajul.sample.application.port.in.model.Sample2DetailQuery;
import com.dongajul.sample.application.port.in.model.SampleDetailQuery;
import com.dongajul.sample.application.port.in.model.SampleSampleDetailQuery;

class SampleService implements RegisterSampleUseCase,
        ReadSampleDetail {

    @Override
    public void read(SampleDetailQuery query) {
        if (query instanceof Sample2DetailQuery detailQuery) {

        } else if (query instanceof SampleSampleDetailQuery detailQuery) {

        } else {
            throw new IllegalArgumentException("님 서비스 잘못 사용하셨어요.. ㅡㅡ;;");
        }
    }


}
