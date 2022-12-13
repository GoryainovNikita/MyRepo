import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

    public class LetterCount {

        private Word[] word;


        public LetterCount(String sentence) {
            String[] rawWords = sentence.split(" ");
            this.word = new Word[rawWords.length];
            for (int i = 0; i < rawWords.length; i++) {
                LetterCount.Word tmp = new LetterCount.Word(rawWords[i]);
                word[i] = tmp;
            }
        }


        public class Word {
            String data;

            Word(String data) {
                this.data = data;
            }

            public HashMap<Character, Integer> counterWord() {
                char[] arrLine = new char[data.length()];
                arrLine = data.toCharArray();
                int count = 1;
                HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
                ArrayList<Character> arrayList = new ArrayList<>();
                for (int i = 0; i < arrLine.length; i++) {
                    arrayList.add(arrLine[i]);
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    for (int j = i + 1; j < arrayList.size(); j++) {
                        if (arrayList.get(i).equals(arrayList.get(j))) {
                            count++;
                            arrayList.remove(j);
                            j--;
                        }
                    }
                    hashMap.put(arrayList.get(i), count);
                    count = 1;
                }
                return hashMap;
            }
        }

        public HashMap<Character, Integer> counterAllWords() {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (var elem : word) {
                HashMap<Character, Integer> map = elem.counterWord();
                for (var elem1 : map.keySet()) {
                    hashMap.computeIfPresent(elem1, (k, v) -> v + map.get(elem1));
                    hashMap.putIfAbsent(elem1, map.get(elem1));
                }
            }

            System.out.print("Индивидуальные буквы: ");
            for (Map.Entry<Character, Integer> mapE : hashMap.entrySet()) {
                System.out.print(mapE.getKey() + ", ");
            }
            System.out.println("\nКоличество вхождений каждой буквы в заданой строке:");
            for (Map.Entry<Character, Integer> mapE : hashMap.entrySet()) {
                System.out.println("буква \"" + mapE.getKey() + "\" - " + mapE.getValue());
            }
            return hashMap;
        }


    }

