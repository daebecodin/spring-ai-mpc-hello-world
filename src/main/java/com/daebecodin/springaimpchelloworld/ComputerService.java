package com.daebecodin.springaimpchelloworld;


import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ComputerService {

    private static final Logger logger = LoggerFactory.getLogger(ComputerService.class);
    private List<Computer> computers = new ArrayList<>();
    private static final Random rand = new Random();


    // tool to expose for all computers
    @Tool(name = "dae_get_computers", description = "return all computers in the database")
    public List<Computer> getComputers() {
        return computers;
    }

    // tool to expose for computers by gpu
    @Tool(name = "dae_get_computer_by_gpu", description = "get all pcs that include the uses specified gpu")
    public List<Computer> getComputerByGpu(String gpu) {
        return Collections.singletonList(computers.stream().filter(c -> c.gpu().equals(gpu)).findFirst().orElse(null));
    }
    // tool for computers by cpu
    @Tool(name = "dae_get_computer_by_cpu", description = "get all pcs that include the uses specified cpu")
    public List<Computer> getComputerByCpu(String cpu) {
        return Collections.singletonList(computers.stream().filter(c -> c.cpu().equals(cpu)).findFirst().orElse(null));
    }

    // schedule a computer pickup


    @PostConstruct
    void init() {

        // list for motherboards
        List<String> motherboards = List.of(
                "ASUS ROG Strix B550-F Gaming",
                "MSI MAG X570 TOMAHAWK",
                "Gigabyte Aorus B450 Elite"
        );

        // list for cpu
        List<String> cpus = List.of(
                "AMD Ryzen 5 5600X",
                "AMD Ryzen 7 5800X",
                "Intel Core i7-12700K"
        );

        // list for gpu
        List<String> gpus = List.of(
                "NVIDIA RTX 3080",
                "NVIDIA RTX 3070 Ti",
                "AMD Radeon RX 6800 XT"
        );

        // list for ram
        List<String> rams = List.of(
                "16GB DDR4-3200",
                "32GB DDR4-3600",
                "16GB DDR4-4000"
        );

        // list for cooler
        List<String> coolers = List.of(
                "Corsair iCUE H100i RGB",
                "Noctua NH-D15",
                "be quiet! Dark Rock Pro 4"
        );

        // list for pcCase
        List<String> cases = List.of(
                "NZXT H510",
                "Fractal Design Meshify C",
                "Phanteks Eclipse P400A"
        );

        // create 30 computers
        for (int i = 0; i < 30; i++) {
            // random mobo from the list for the current iteration
            String mobo = motherboards.get(rand.nextInt(motherboards.size()));
            // random cpu from the list for the current iteration
            String cpu = cpus.get(rand.nextInt(cpus.size()));
            // random gpu from the list for the current iteration
            String gpu = gpus.get(rand.nextInt(gpus.size()));
            // random ram from the list for the current iteration
            String ram = rams.get(rand.nextInt(rams.size()));
            // random cooler from the list for the current iteration
            String cooler = coolers.get(rand.nextInt(coolers.size()));
            // random case from the list for the current iteration
            String pcCase = cases.get(rand.nextInt(cases.size()));


            // create 30 computers with random config
        computers.add(
                new Computer(
                        "PC" + i,
                        mobo,
                        cpu,
                        gpu,
                        ram,
                        cooler,
                        pcCase
                )
        );
        }
        logger.info("Initialized {} gaming pc's", computers.size());
    }
}
