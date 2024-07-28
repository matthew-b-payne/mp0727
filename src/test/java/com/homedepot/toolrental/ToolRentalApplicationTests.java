package com.homedepot.toolrental;

import com.homedepot.toolrental.repository.ToolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ToolRentalApplicationTests {

    @MockBean
    private ToolRepository toolRepository;

    @Test
    void contextLoads() {
    }

}
