package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class FileAttachTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileAttach.class);
        FileAttach fileAttach1 = new FileAttach();
        fileAttach1.setId(1L);
        FileAttach fileAttach2 = new FileAttach();
        fileAttach2.setId(fileAttach1.getId());
        assertThat(fileAttach1).isEqualTo(fileAttach2);
        fileAttach2.setId(2L);
        assertThat(fileAttach1).isNotEqualTo(fileAttach2);
        fileAttach1.setId(null);
        assertThat(fileAttach1).isNotEqualTo(fileAttach2);
    }
}
