package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class OfficerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Officer.class);
        Officer officer1 = new Officer();
        officer1.setId(1L);
        Officer officer2 = new Officer();
        officer2.setId(officer1.getId());
        assertThat(officer1).isEqualTo(officer2);
        officer2.setId(2L);
        assertThat(officer1).isNotEqualTo(officer2);
        officer1.setId(null);
        assertThat(officer1).isNotEqualTo(officer2);
    }
}
