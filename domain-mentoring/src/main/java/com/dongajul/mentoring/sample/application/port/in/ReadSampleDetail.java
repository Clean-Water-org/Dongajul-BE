package com.dongajul.mentoring.sample.application.port.in;

import com.dongajul.mentoring.sample.application.port.in.model.SampleDetailQuery;

public interface ReadSampleDetail {

    void read(SampleDetailQuery query);
}
