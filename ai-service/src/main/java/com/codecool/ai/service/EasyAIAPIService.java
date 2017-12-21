package com.codecool.ai.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EasyAIAPIService implements AiService {

    @Override
    public Integer getRecommendation(String board, String player) {
        Integer result = null;
        int optionCount = countOccurrences(board);
        if (optionCount == 0) {
            return null;
        }
        Random rnd = new Random();
        int rndChoice = rnd.nextInt(optionCount);

        char[] boardChars = board.toCharArray();
        for (int i = 0; i < boardChars.length; i++) {
            if (boardChars[i] == '-') {
                if (rndChoice == 0) {
                    result = i;
                    break;
                }
                rndChoice--;
            }
        }

        return result;
    }

    private static int countOccurrences(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '-') {
                count++;
            }
        }
        return count;
    }

}
