import 'package:flutter/material.dart';
import 'package:humantalent/employees.dart';
import 'package:humantalent/registration.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'HumanTalent App!',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.grey),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'HumanTalent App!'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _currentIndex = 0;
  bool _showMenu = false;

  final pages = [
    employees(),
    registration(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFF212529),
        title: Text(
          widget.title,
          style:
              const TextStyle(fontWeight: FontWeight.bold, color: Colors.white),
        ),
        centerTitle: true,
        leading: IconButton(
          onPressed: () => {},
          icon: IconButton(
            icon: const Icon(Icons.menu),
            color: Colors.white,
            onPressed: () => {
              setState(() {
                _showMenu = !_showMenu;
              })
            },
          ),
        ),
      ),
      body: Stack(
        fit: StackFit.expand,
        alignment: Alignment.topLeft,
        children: <Widget>[
          Center(
            child: pages[_currentIndex],
          ),
          if (_showMenu)
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                InkWell(
                  onTap: () {
                    setState(() {
                      _currentIndex = 0;
                      _showMenu = !_showMenu;
                    });
                  },
                  child: Container(
                    width: double.infinity,
                    color: Colors.white,
                    padding: const EdgeInsets.all(
                      20,
                    ),
                    child: const Text("Employees"),
                  ),
                ),
                InkWell(
                  onTap: () {
                    setState(() {
                      _currentIndex = 1;
                      _showMenu = !_showMenu;
                    });
                  },
                  child: Container(
                    width: double.infinity,
                    color: Colors.white,
                    padding: const EdgeInsets.all(
                      20,
                    ),
                    child: const Text("Registration"),
                  ),
                ),
                Expanded(
                  child: Container(
                    color: Colors.black.withOpacity(.2),
                  ),
                )
              ],
            ),
        ],
      ),
    );
  }
}
