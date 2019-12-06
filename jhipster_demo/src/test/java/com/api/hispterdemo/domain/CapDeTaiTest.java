package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class CapDeTaiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CapDeTai.class);
        CapDeTai capDeTai1 = new CapDeTai();
        capDeTai1.setId(1L);
        CapDeTai capDeTai2 = new CapDeTai();
        capDeTai2.setId(capDeTai1.getId());
        assertThat(capDeTai1).isEqualTo(capDeTai2);
        capDeTai2.setId(2L);
        assertThat(capDeTai1).isNotEqualTo(capDeTai2);
        capDeTai1.setId(null);
        assertThat(capDeTai1).isNotEqualTo(capDeTai2);
    }
}
