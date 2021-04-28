/* Kornilov Nikita, M3102, 18.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;
    ArrayList<Character> res;

    public static void main(String[] args) {
        new Task3().run();
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            String inputString = br.readLine();
            if (inputString != null) {
                in = new StringTokenizer(inputString);
            } else {
                return null;
            }
        }
        return in.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        char[] s1 = nextToken().toCharArray(), s2 = nextToken().toCharArray();
        res = new ArrayList<>();

        Hirschberg(s1, s2);

        for (char c : res) {
            out.print(c);
        }
        out.println();

    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private short[] LCS(char[] s1, char[] s2) {
        short[] current = new short[s2.length + 1];

        for (int i = 0; i < s1.length ; i++) {
            short[] prev = new short[s2.length + 1];
            System.arraycopy(current, 0, prev, 0, s2.length + 1);

            for (int j = 0; j < s2.length; j++) {
                if (s1[i] == s2[j]) {
                    current[j + 1] = (short) (prev[j] + 1);
                } else {
                    current[j + 1] = (short) Math.max(current[j], prev[j + 1]);
                }
            }
        }

        return current;
    }

    private void Hirschberg(char[] s1, char[] s2) {
        if (s1.length == 0) {
        } else if (s1.length == 1) {
            if (Arrays.toString(s2).indexOf(s1[0]) != -1) {
                res.add(s1[0]);
            }
        } else {
            int separatorFirst = s1.length / 2;
            char[] s1First = slice(s1, 0, separatorFirst), s1Second = slice(s1, separatorFirst, s1.length);

            short[] first = LCS(s1First, s2), second = reverse(LCS(reverse(s1Second), reverse(s2)));

            for (int i = 0; i < first.length; i++) {
                first[i] += second[i];
            }

            int separatorSecond = findMaxIndex(first);
            char[] s2First = slice(s2, 0, separatorSecond), s2Second = slice(s2, separatorSecond, s2.length);

            Hirschberg(s1First, s2First);
            Hirschberg(s1Second, s2Second);
        }
    }

    private char[] reverse(char[] array) {
        char[] reversedArray = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - i - 1];
        }

        return reversedArray;
    }

    private short[] reverse(short[] array) {
        short tmp;

        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }

        return array;
    }

    private short findMaxIndex(short[] array) {
        short max = array[0], maxIndex = 0;

        for (short i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private char[] slice(char[] array, int startIndex, int length) {
        return Arrays.copyOfRange(array, startIndex, length);
    }

}

/*
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<char> ans;

int findMaxIndex(vector<int> &array) {
    int max = array[0], maxIndex = 0;

    for (int i = 0; i < array.size(); i++) {
        if (max < array[i]) {
            max = array[i];
            maxIndex = i;
        }
    }

    return maxIndex;
}

vector<int> lcs(vector<char> &s1, vector<char> &s2) {
    vector<int> current = vector<int>(s2.size() + 1);

    for (int i = 0; i < s1.size(); i++) {
        vector<int> prev = current;

        for (int j = 0; j < s2.size(); j++) {
            if (s1[i] == s2[j]) {
                current[j + 1] = prev[j] + 1;
            } else {
                current[j + 1] = max(current[j], prev[j + 1]);
            }
        }
    }

    return current;
}

void Hirschberg(vector<char> s1, vector<char> s2) {
    if (s1.size() == 0) {

    } else if (s1.size() == 1) {
        if (find(s2.begin(), s2.end(), s1[0]) != s2.end()) {
            ans.push_back(s1[0]);
        }
    } else {
        int separatorFirst = s1.size() / 2;
        vector<char> s1First = vector<char>(s1.begin(), s1.begin() + separatorFirst);
        vector<char> s1Second = vector<char>(s1.begin() + separatorFirst, s1.end());

        vector<int> first = lcs(s1First, s2);

        reverse(s1Second.begin(), s1Second.end());
        reverse(s2.begin(), s2.end());
        vector<int> second = lcs(s1Second, s2);
        reverse(second.begin(), second.end());
        reverse(s1Second.begin(), s1Second.end());
        reverse(s2.begin(), s2.end());

        for (int i = 0; i < first.size(); i++) {
            first[i] += second[i];
        }

        int separatorSecond = findMaxIndex(first);

        vector<char> s2First = vector<char>(s2.begin(), s2.begin() + separatorSecond);
        vector<char> s2Second = vector<char>(s2.begin() + separatorSecond, s2.end());

        Hirschberg(s1First, s2First);
        Hirschberg(s1Second, s2Second);

    }
}

int main() {
    string s1, s2;
    getline(cin, s1);
    getline(cin, s2);
    vector<char> s1V, s2V;

    copy(s1.begin(), s1.end(), back_inserter(s1V));
    copy(s2.begin(), s2.end(), back_inserter(s2V));

    Hirschberg(s1V, s2V);

    for (char c : ans) {
        cout << c;
    }
    cout << endl;

    return 0;
}




 */